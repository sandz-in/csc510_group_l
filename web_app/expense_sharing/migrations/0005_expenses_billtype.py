# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('expense_sharing', '0004_auto_20160217_1925'),
    ]

    operations = [
        migrations.AddField(
            model_name='expenses',
            name='billtype',
            field=models.CharField(max_length=128, null=True),
        ),
    ]
